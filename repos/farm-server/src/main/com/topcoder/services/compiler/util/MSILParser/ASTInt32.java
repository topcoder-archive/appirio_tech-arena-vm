/* Generated By:JJTree: Do not edit this line. ASTInt32.java */

package com.topcoder.services.compiler.util.MSILParser;

public class ASTInt32 extends SimpleNode {
  public ASTInt32(int id) {
    super(id);
  }

  public ASTInt32(MSILParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MSILParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
