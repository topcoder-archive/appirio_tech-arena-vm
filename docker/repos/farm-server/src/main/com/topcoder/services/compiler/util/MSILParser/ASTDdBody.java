/* Generated By:JJTree: Do not edit this line. ASTDdBody.java */

package com.topcoder.services.compiler.util.MSILParser;

public class ASTDdBody extends SimpleNode {
  public ASTDdBody(int id) {
    super(id);
  }

  public ASTDdBody(MSILParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MSILParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
