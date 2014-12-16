/*
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2006 - 2009 Pentaho Corporation..  All rights reserved.
 */
package org.pentaho.pms.schema.concept.types.bool;

import org.pentaho.metadata.model.concept.Property;
import org.pentaho.pms.schema.concept.types.ConceptPropertyBase;
import org.pentaho.pms.schema.concept.types.ConceptPropertyType;

/**
 * @deprecated as of metadata 3.0.
 */
public class ConceptPropertyBoolean extends ConceptPropertyBase implements Cloneable {
  private Boolean value;

  public ConceptPropertyBoolean( String name, Boolean value ) {
    this( name, null != value ? value.booleanValue() : false );
  }

  public ConceptPropertyBoolean( String name, boolean value ) {
    this( name, value, false );
  }

  public ConceptPropertyBoolean( String name, boolean value, boolean required ) {
    super( name, required );
    this.value = new Boolean( value );
  }

  public String toString() {
    if ( value == null ) {
      return null;
    }
    return value.booleanValue() ? "Y" : "N"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public Object clone() throws CloneNotSupportedException {
    ConceptPropertyBoolean rtn = (ConceptPropertyBoolean) super.clone();
    if ( value != null ) {
      rtn.value = new Boolean( value.booleanValue() );
    }
    return rtn;
  }

  public ConceptPropertyType getType() {
    return ConceptPropertyType.BOOLEAN;
  }

  public Property getValue() {
    return new Property<Boolean>( value );
  }

  public void setValue( Property value ) {
    this.value = value != null ? (Boolean) value.getValue() : null;
  }

  public boolean equals( Object obj ) {
    return value.equals( obj );
  }

  public int hashCode() {
    return value.hashCode();
  }
}
