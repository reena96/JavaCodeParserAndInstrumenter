package in.naishe.algo;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.io.IOException;
import java.util.List;

//import in.naishe.algo.MyASTVisitorImpl;
import in.naishe.algo.search.BinarySearchFirstOccurrence;
import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;


public class ASTCreateTree {


    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
        ASTCreateTree  test = new ASTCreateTree();
        File filepath = new File("./src/main/java/in/naishe/algo/Test.java");
        //File file_new = new File("./src/main/java/in/naishe/algo/Test1.java");

        test.createASTTree(filepath);
    }

    public void createASTTree(File file) throws IOException, MalformedTreeException, BadLocationException, CoreException {
        String str = FileUtils.readFileToString(file);
        ASTParser parser = ASTParser.newParser(AST.JLS4);
        parser.setResolveBindings(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        Map options = JavaCore.getOptions();
        parser.setCompilerOptions(options);
        String unitName = "MaximumSubarray.java";
        parser.setUnitName(unitName);
        String[] sources = { "./src/main/java/in/naishe/algo" };
        String[] classpath = {};
        // sources is the source dir of all your java files
        // classpath is the java jar containing all the needed classes
        parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
        parser.setSource(str.toCharArray());

        CompilationUnit unit = (CompilationUnit) parser.createAST(null);
        System.out.println("Finished creating Abstract Syntax Tree");

        MyASTVisitor visitor = new MyASTVisitor();
        visitor.computeScopes(unit);
        visitor.printScopeTable();
        visitor.addTemplateCode(unit,file);
        //unit.recordModifications();


    }
}


