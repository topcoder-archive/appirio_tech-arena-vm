/* Generated By:JJTree: Do not edit this line. ASTFilename.java */

package com.topcoder.services.compiler.util.MSILParser;

public class ASTFilename extends SimpleNode {
  public ASTFilename(int id) {
    super(id);
  }

  public ASTFilename(MSILParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MSILParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
