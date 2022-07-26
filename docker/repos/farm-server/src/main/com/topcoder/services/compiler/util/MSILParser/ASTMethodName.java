/* Generated By:JJTree: Do not edit this line. ASTMethodName.java */

package com.topcoder.services.compiler.util.MSILParser;

public class ASTMethodName extends SimpleNode {
  public ASTMethodName(int id) {
    super(id);
  }

  public ASTMethodName(MSILParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MSILParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
  
  private String name = null;
  
  public void setName(String s) {
      this.name = s;
  }
  
  public String getName() {
      if(name != null)
          return name;
      
      for(int i =0  ; i < children.length; i++) {
          if(children[i] instanceof ASTDottedName) {
                return ((ASTDottedName)children[i]).getName();
          }
      }
      
      return null;
  }
}
