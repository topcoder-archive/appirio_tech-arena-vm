/* Generated By:JJTree: Do not edit this line. ASTILFile.java */

package com.topcoder.services.compiler.util.MSILParser;

public class ASTILFile extends SimpleNode {
  public ASTILFile(int id) {
    super(id);
  }

  public ASTILFile(MSILParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MSILParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
