package com.topcoder.shared.language;

import com.topcoder.shared.problem.DataType;

/**
 * Implements the <code>Language</code> interface for the Python programming language.
 * 
 * @author Qi Liu
 * @version $Id: PythonLanguage.java 71732 2008-07-16 05:53:02Z qliu $
 */
public class PythonLanguage extends BaseLanguage {
    /** Represents the unique ID for Python. */
    public final static int ID = 6;

    /** Represents the description for Python. */
    public final static String DESCRIPTION = "Python";

    /** Represents the singleton instance for Python language. */
    public final static PythonLanguage PYTHON_LANGUAGE = new PythonLanguage();

    /**
     * Creates a new instance of <code>PythonLanguage</code> class. A default constructor must be available as
     * required by custom serialization. Developers should not directly use this constructor, but use
     * <code>Language.getLanguage</code>
     * 
     * @see Language.getLanguage
     */
    public PythonLanguage() {
        super(ID, DESCRIPTION);
    }

    /**
     * Gets the method signature for this programming language according to the given method name, return type, argument
     * types and argument names. The returned Python method signature is like the following: <code>'def
     * METHOD_NAME(self, PARAM_NAME_1, PARAM_NAME_2, ..., PARAM_NAME_n):'</code>.
     * 
     * @param methodName the method name.
     * @param returnType the return type of the method.
     * @param paramTypes the argument types of the method.
     * @param paramNames the argument names of the method.
     * @return the method signature.
     * @throws IllegalArgumentException if the numbers of elements in <code>paramTypes</code> and
     *             <code>paramNames</code> are different.
     */
    public String getMethodSignature(String methodName, DataType returnType, DataType[] paramTypes, String[] paramNames) {
        if (paramTypes.length != paramNames.length)
            throw new IllegalArgumentException(
                "PythonLanguage.getMethodSignature: paramTypes.length != paramNames.length (" + paramTypes.length
                    + " + " + paramNames.length + ")");

        String returns = returnType.getDescriptor(this);
        String[] params = new String[paramTypes.length];
        int len = returns.length() + methodName.length() + 3; // 3 = ' ' + '(' + ')'

        for (int i = 0; i < params.length; i++) {
            paramTypes[i].getDescriptor(this);
            params[i] = paramNames[i];
            len += params[i].length();
        }
        len += 2 * (params.length - 1);

        StringBuffer buf = new StringBuffer(len);

        buf.append("def ");
        buf.append(methodName);
        buf.append("(self, ");
        for (int i = 0; i < params.length; i++) {
            if (i > 0) {
                buf.append(", ");
            }
            buf.append(params[i]);
        }
        buf.append("):");
        return buf.toString();
    }

    /**
     * Gets a string representing the calling of the given method in the given class using the given variable names as
     * arguments. The Python calling is like the following:
     * <code>'val = CLASS_NAME.METHOD_NAME(PARAM_NAME_1, PARAM_NAME_2, ..., PARAM_NAME_n)'</code>.
     * 
     * @param className the class name where the called method is located.
     * @param methodName the method name to be called.
     * @param paramNames the variable names to be used as the arguments.
     * @return a string representation of the call.
     */
    public String exampleExposedCall(String className, String methodName, String[] paramNames) {
        StringBuffer buf = new StringBuffer();

        buf.append("val = ");
        buf.append(className);
        buf.append(".");
        buf.append(methodName);
        buf.append("(");

        for (int i = 0; i < paramNames.length; i++) {
            if (i > 0)
                buf.append(", ");
            buf.append(paramNames[i]);
        }

        buf.append(")");

        return buf.toString();
    }

    /**
     * Gets the default file extension for Python, which is 'py'.
     * 
     * @return a string 'py'.
     */
    public String getDefaultExtension() {
        return "py";
    }
}