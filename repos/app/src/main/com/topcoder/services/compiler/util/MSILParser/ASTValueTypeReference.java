/* Generated By:JJTree: Do not edit this line. ASTValueTypeReference.java */

package com.topcoder.services.compiler.util.MSILParser;

public class ASTValueTypeReference extends SimpleNode {
  public ASTValueTypeReference(int id) {
    super(id);
  }

  public ASTValueTypeReference(MSILParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MSILParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
