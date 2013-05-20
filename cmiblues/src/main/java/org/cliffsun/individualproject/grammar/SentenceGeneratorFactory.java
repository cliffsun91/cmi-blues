package org.cliffsun.individualproject.grammar;

/**
*
* Object Factory that is used to coerce python module into a
* Java class
*/
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class SentenceGeneratorFactory {

   private PyObject sentenceGenerator;

   /**
    * Create a new PythonInterpreter object, then use it to
    * execute some python code. In this case, we want to
    * import the python module that we will coerce.
    *
    * Once the module is imported than we obtain a reference to
    * it and assign the reference to a Java variable
    */

   public SentenceGeneratorFactory(String jythonPath) {
       PythonInterpreter interpreter;// = new PythonInterpreter();

       PySystemState sys = Py.getSystemState();
       String pyparsingPath = "/Lib/site-packages/pyparsing-1.5.7-py2.5.egg";
       sys.path.append(new PyString(jythonPath + pyparsingPath));
       
       interpreter = new PythonInterpreter(null, sys);
       interpreter.exec("from grammar.SentenceGeneratorWrapper import SentenceGeneratorWrapper");
       sentenceGenerator = interpreter.get("SentenceGeneratorWrapper");
   }

   /**
    * The create method is responsible for performing the actual
    * coercion of the referenced python module into Java bytecode
    */

   public SentenceGenerator create(String grammarFilePath) {
       PyObject exampleObject = sentenceGenerator.__call__(new PyString(grammarFilePath));
       return (SentenceGenerator) exampleObject.__tojava__(SentenceGenerator.class);
   }

}