package in.naishe.algo;

import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.util.*;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.projection.Fragment;
import org.eclipse.text.edits.TextEdit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MyASTVisitor {

    CompilationUnit unit;
    HashMap<String, String> scope_map = new HashMap<String, String>();
    MethodDeclaration[] methodDeclarationList;
    FieldDeclaration[] fieldDeclarationList;
    List<VariableDeclarationFragment> globalVariableFragmentList;
    String globalVariables = "";
    String package_name = "";
    String class_name = "";
    int k = 0;


    public void computeScopes(CompilationUnit unit) {
        this.unit = unit;
        unit.accept(new ASTVisitor() {


            @Override
            public boolean visit(TypeDeclaration node) {

                scope_map.put(node.getName().toString(), "");

                methodDeclarationList = node.getMethods();
                for (MethodDeclaration method : methodDeclarationList) {
                    scope_map.put(method.getName().toString(), "");
                }

                fieldDeclarationList = node.getFields();
                for (FieldDeclaration field : fieldDeclarationList) {
                    globalVariableFragmentList = field.fragments();

                    for (VariableDeclarationFragment fragment : globalVariableFragmentList) {
                        //System.out.println(fragment);
                        if (globalVariables == "")
                            globalVariables = fragment.getName().getIdentifier();
                        else
                            globalVariables += " , " + fragment.getName().getIdentifier();
                    }
                }

                for (Map.Entry m : scope_map.entrySet()) {
                    if (m.getValue() == "")
                        scope_map.put(m.getKey().toString(), globalVariables);
                    else
                        scope_map.put(m.getKey().toString(), m.getValue() + " , " + globalVariables);
                }

                for (int i = 0; i < methodDeclarationList.length; i++) {
                    MethodDeclaration method = methodDeclarationList[i];
                    Block block = method.getBody();
                    method.getBody().statements();

                    block.accept(new ASTVisitor() {

                        public boolean visit(VariableDeclarationFragment var) {
                            String key = method.getName().toString();
                            if (scope_map.get(key) == "")
                                scope_map.put(key, var.getName().getIdentifier()); // variable name
                            else
                                scope_map.put(key, scope_map.get(key) + " , " + var.getName().getIdentifier()); // variable name


                            return false;
                        }

                        @Override
                        public boolean visit(IfStatement ifStatement) {
                            ifStatement.accept(new ASTVisitor() {

                                public boolean visit(VariableDeclarationFragment var) {
                                    //System.out.println(var.resolveBinding().getDeclaringMethod());
                                    //System.out.println("Variable " + var.getName() + ", in Method " + method.getName() + "' Method line " + unit.getLineNumber(method.getStartPosition()));
                                    String key = package_name + "." + method.getName() + "() : if(" + ifStatement.getExpression().toString() + ") {}";
                                    //System.out.println(key + " : " + scope_map.get(key));
                                    String value = scope_map.get(key);
                                    if (value == "" || value == null)
                                        scope_map.put(key, var.getName().getIdentifier()); // variable name
                                    else
                                        scope_map.put(key, scope_map.get(key) + " , " + var.getName().getIdentifier()); // variable name
                                    return false;
                                }
                            });
                            return super.visit(ifStatement);
                        }

                        @Override
                        public boolean visit(WhileStatement whileStatement) {
                            whileStatement.accept(new ASTVisitor() {

                                public boolean visit(VariableDeclarationFragment var) {
                                    //System.out.println(var.resolveBinding().getDeclaringMethod());
                                    //System.out.println("Variable " + var.getName() + ", in Method " + method.getName() + "' Method line " + unit.getLineNumber(method.getStartPosition()));
                                    String key = package_name + "." + method.getName() + " : while(" + whileStatement.getExpression().toString() + ") {}";
                                    //System.out.println(key + " : " + scope_map.get(key));
                                    String value = scope_map.get(key);
                                    if (value == "" || value == null)
                                        scope_map.put(key, var.getName().getIdentifier()); // variable name
                                    else
                                        scope_map.put(key, scope_map.get(key) + " , " + var.getName().getIdentifier()); // variable name
                                    return false;
                                }

                            });
                            return super.visit(whileStatement);
                        }
                    });
                }

                return super.visit(node);
            }
        });
    }


    public void addTemplateCode(CompilationUnit unit, File file_new) {

        this.unit = unit;
        unit.accept(new ASTVisitor() {
            @Override
            public boolean visit(PackageDeclaration node) {
                package_name = node.getName().toString();
                return super.visit(node);
            }
        });
        AST ast = unit.getAST();
        ASTRewrite rewrite = ASTRewrite.create(ast);

        unit.accept(new ASTVisitor() {

            @Override
            public boolean visit(TypeDeclaration node) {
                class_name = node.getName().toString();
                methodDeclarationList = node.getMethods();

                for (int i = 0; i < methodDeclarationList.length; i++) {
                    MethodDeclaration method = methodDeclarationList[i];
                    method.accept(new ASTVisitor() {
                        @Override
                        public boolean visit(Block block) {
                            ListRewrite listRewrite = rewrite.getListRewrite(block, Block.STATEMENTS_PROPERTY);

                            for (Statement s : (List<Statement>) block.statements()) {
                                String value[] = s.getClass().toString().split("\\.");
                                String statement_type = value[value.length - 1];

                                int l = unit.getLineNumber(s.getStartPosition());

                                if (s instanceof ExpressionStatement) {

                                    s.accept(new ASTVisitor() {
                                        @Override
                                        public boolean visit(Assignment assignment) {

                                            //System.out.println(package_name + "." + class_name + "." + method.getName() + "." + assignment.getLeftHandSide());
                                            assignment.getRightHandSide().accept(new ASTVisitor() {
                                                @Override
                                                public boolean visit(InfixExpression rightSide) {


                                                    String preName = package_name + "." + class_name + "." + method.getName().toString();
                                                    MethodInvocation methodInvocation = ast.newMethodInvocation();
                                                    SimpleName qName = ast.newSimpleName("Template");
                                                    methodInvocation.setExpression(qName);
                                                    methodInvocation.setName(ast.newSimpleName("instrum"));
                                                    StringLiteral literal1 = ast.newStringLiteral();
                                                    StringLiteral literal2 = ast.newStringLiteral();

                                                    literal1.setLiteralValue(String.valueOf(l));
                                                    literal2.setLiteralValue(String.valueOf(statement_type));
                                                    methodInvocation.arguments().add(literal1);
                                                    methodInvocation.arguments().add(literal2);

                                                    StringLiteral literal3 = ast.newStringLiteral();
                                                    SimpleName literal4;
                                                    if (assignment.getLeftHandSide() != null) {
                                                        literal4 = ast.newSimpleName(assignment.getLeftHandSide().toString());
                                                        methodInvocation.arguments().add(literal4);
                                                    }
                                                    StringLiteral literal5 = ast.newStringLiteral();
                                                    SimpleName literal6;
                                                    if (assignment.getRightHandSide() != null && rightSide.getLeftOperand() != null) {
                                                        literal6 = ast.newSimpleName(rightSide.getLeftOperand().toString());
                                                        methodInvocation.arguments().add(literal6);
                                                    }
                                                    SimpleName literal8;
                                                    if (assignment.getRightHandSide() != null && rightSide.getRightOperand() != null) {
                                                        literal8 = ast.newSimpleName(rightSide.getRightOperand().toString());
                                                        methodInvocation.arguments().add(literal8);
                                                    }
                                                    StringLiteral literal7 = ast.newStringLiteral();
                                                    literal3.setLiteralValue(preName + "." + assignment.getLeftHandSide().toString());
                                                    literal5.setLiteralValue(preName + "." + rightSide.getLeftOperand().toString());
                                                    literal7.setLiteralValue(preName + "." + rightSide.getRightOperand().toString());

                                                    methodInvocation.arguments().add(literal3);
                                                    methodInvocation.arguments().add(literal5);
                                                    methodInvocation.arguments().add(literal7);
                                                    //System.out.println("METHOD INVOCATION : " + methodInvocation);

                                                    ASTNode current_statement = s;
                                                    ExpressionStatement statement1 = ast.newExpressionStatement(methodInvocation);
                                                    listRewrite.insertAfter(statement1, current_statement, null);
                                                    return super.visit(node);
                                                }
                                            });
                                            return super.visit(node);
                                        }
                                    });
                                    if (((ExpressionStatement) s).getExpression().toString().contains("System.out.println")) {
                                        continue;
                                    }
                                }
                            }
                            return super.visit(block);
                        }
                    });
                }
                return super.visit(node);
            }
        });
        String str = null;
        try {
            str = FileUtils.readFileToString(file_new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = new Document(str);
        TextEdit edits = rewrite.rewriteAST(document, null);
        try {
            edits.apply(document);
            //System.out.println(document.get() );
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        try {
            FileUtils.writeStringToFile(file_new, document.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void printScopeTable() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Computation of Scopes & Determination of variables declared in scopes: ");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Map.Entry m : scope_map.entrySet()) {

            k = k + 1;
            System.out.println(k + ". Scope Name        :  " + m.getKey());
            System.out.println("   Variables in scope:  " + m.getValue());
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

}