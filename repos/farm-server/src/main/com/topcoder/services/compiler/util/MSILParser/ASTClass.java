/* Generated By:JJTree: Do not edit this line. ASTClass.java */

package com.topcoder.services.compiler.util.MSILParser;

public class ASTClass extends SimpleNode {
  public ASTClass(int id) {
    super(id);
  }

  public ASTClass(MSILParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MSILParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
