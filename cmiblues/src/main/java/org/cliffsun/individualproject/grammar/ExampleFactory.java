package org.cliffsun.individualproject.grammar;

/**
*
* Object Factory that is used to coerce python module into a
* Java class
*/
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class ExampleFactory {

   private PyObject grammarExampleClass;

   /**
    * Create a new PythonInterpreter object, then use it to
    * execute some python code. In this case, we want to
    * import the python module that we will coerce.
    *
    * Once the module is imported than we obtain a reference to
    * it and assign the reference to a Java variable
    */

   public ExampleFactory() {
       PythonInterpreter interpreter = new PythonInterpreter();
       interpreter.exec("from grammar.GrammarExample import GrammarExample");
       grammarExampleClass = interpreter.get("GrammarExample");
   }

   /**
    * The create method is responsible for performing the actual
    * coercion of the referenced python module into Java bytecode
    */

   public Example create() {
       PyObject exampleObject = grammarExampleClass.__call__();
       return (Example) exampleObject.__tojava__(Example.class);
   }

}