/* Generated By:JJTree: Do not edit this line. ASTCallKind.java */

package com.topcoder.services.compiler.util.MSILParser;

public class ASTCallKind extends SimpleNode {
  public ASTCallKind(int id) {
    super(id);
  }

  public ASTCallKind(MSILParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MSILParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
