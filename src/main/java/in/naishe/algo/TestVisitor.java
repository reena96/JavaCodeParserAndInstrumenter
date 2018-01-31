package in.naishe.algo;

import in.naishe.algo.tree.Node;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.*;

import com.sun.javafx.css.Declaration;
import com.sun.tools.internal.ws.wsdl.document.Import;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ImportDeclaration;

import java.util.*;

public class TestVisitor{

CompilationUnit unit;
static private List  sourcecodeStatObj = new LinkedList();

    public void testVisit(CompilationUnit unit) {
        this.unit = unit;
        unit.accept(new ASTVisitor() {
            int forcount=1;

            /* ------------------------  */
            @Override
            public boolean visit(ForStatement node)
            {
                List<String> initializer = node.initializers();
                for (Iterator it = node.initializers().iterator(); it.hasNext(); )
                {
                    Expression e = (Expression) it.next();
                    System.out.println(e.toString());

                }
                Expression exp = node.getExpression();
                String expression = "";
                List updater = node.updaters();
                int length = node.getBody().getLength();
                System.out.println(length);
                int startposition = node.getStartPosition();


                int startLineNumber = unit.getLineNumber(node.getStartPosition());
                int nodeLength = node.getLength();
                int endLineNumber = unit.getLineNumber(node.getStartPosition() + nodeLength);

                boolean isexpNull = false ;

                forcount += 1;

                if(exp == null)
                {
                    isexpNull = true;
                }




                if(!isexpNull)
                {
                    //this.names.add(exp.toString());
                    expression = exp.toString();
                }

                Map forobj=new LinkedHashMap();
                forobj.put("Node_Type", "FOR_STATEMENT");
                System.out.println("Node_Type- FOR STATEMENT");
                forobj.put("keyword", "for");
                System.out.println("Keyword- FOR");
                forobj.put("Initializers", initializer.toString());
                System.out.println("Initializers"+initializer.toString());
                forobj.put("Expression", expression);
                System.out.println("Expression"+initializer.toString());
                forobj.put("Updater", updater.toString());
                System.out.println("Updater"+updater.toString());

                forobj.put("CF.BeginLine, CF.EndLine", "(" + startLineNumber + ", "  + endLineNumber + ")");

                //String astjsonText = JSONValue.toJSONString(forobj);



                try
                {
                    sourcecodeStatObj.add(forobj);
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }

                //Accept the visit of the other parts
                //super.visit(node);
                if(!isexpNull)
                {
                    exp.accept(this);
                }

                for (Iterator it = node.initializers().iterator(); it.hasNext(); )
                {
                    Expression e = (Expression) it.next();
                    e.accept(this);

                }


                for (Iterator it = node.updaters().iterator(); it.hasNext(); )
                {
                    Expression e = (Expression) it.next();
                    e.accept(this);
                }

                node.getBody().accept(this);


                return false; // continue
            }

            /* ------------------------  */




        });
    }


    public void testVisit(final ICompilationUnit unit){


    }}
