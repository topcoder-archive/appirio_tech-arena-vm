/* Generated By:JJTree: Do not edit this line. ASTClassName.java */

package com.topcoder.services.compiler.util.MSILParser;

public class ASTClassName extends SimpleNode {
  public ASTClassName(int id) {
    super(id);
  }

  public ASTClassName(MSILParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MSILParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
  
  public String getBaseName() {
      ASTDottedName n = (ASTDottedName)this.jjtGetChild(0);
      return n.getName();
  }
}
