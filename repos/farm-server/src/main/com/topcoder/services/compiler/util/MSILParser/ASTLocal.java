/* Generated By:JJTree: Do not edit this line. ASTLocal.java */

package com.topcoder.services.compiler.util.MSILParser;

public class ASTLocal extends SimpleNode {
  public ASTLocal(int id) {
    super(id);
  }

  public ASTLocal(MSILParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MSILParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
