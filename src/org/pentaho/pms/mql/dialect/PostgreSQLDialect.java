/*
 * Copyright 2006 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the Mozilla Public License, Version 1.1, or any later version. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.mozilla.org/MPL/MPL-1.1.txt. The Original Code is the Pentaho 
 * BI Platform.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
*/
package org.pentaho.pms.mql.dialect;

import org.pentaho.pms.core.exception.PentahoMetadataException;
import org.pentaho.reporting.libraries.formula.lvalues.FormulaFunction;

/**
 * PostgreSQL Implementation of Metadata SQL Dialect
 *  
 * @author Will Gorman (wgorman@pentaho.org)
 *
 */
public class PostgreSQLDialect extends DefaultSQLDialect {
  
  public PostgreSQLDialect() {
    super("POSTGRESQL"); //$NON-NLS-1$
    
    // oracle specific date functions 
    supportedFunctions.put("NOW", new DefaultSQLFunctionGenerator(SQLFunctionGeneratorInterface.PARAM_FUNCTION, "now", 0) { //$NON-NLS-1$ //$NON-NLS-2$
      public void generateFunctionSQL(FormulaTraversalInterface formula, StringBuffer sb, String locale, FormulaFunction f) throws PentahoMetadataException {
        sb.append(sql);
      }
    });
  }
  
  /**
   * return PostgreSQL formatted date, date 'YYYY-MM-DD'
   * 
   * @param year 
   * @param month
   * @param day
   * 
   * @return date string
   */
  public String getDateSQL(int year, int month, int day) {
    return "date " + //$NON-NLS-1$
       quoteStringLiteral(year + "-" + displayAsTwoOrMoreDigits(month) + "-" + displayAsTwoOrMoreDigits(day)); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  /**
   * PostgreSQL has a 63 character limit on table name length
   * 
   * @return max table name length
   */
  public int getMaxTableNameLength() {
    return 63;
  }
}
