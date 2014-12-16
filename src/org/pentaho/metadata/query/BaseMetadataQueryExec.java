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
 * Copyright (c) 2010 Pentaho Corporation..  All rights reserved.
 */
package org.pentaho.metadata.query;

import java.util.HashMap;
import java.util.Map;

import org.pentaho.metadata.model.IMetadataQueryExec;
import org.pentaho.metadata.model.IPhysicalModel;
import org.pentaho.metadata.model.concept.Property;
import org.pentaho.metadata.query.model.Parameter;
import org.pentaho.metadata.repository.IMetadataDomainRepository;

/**
 * This is a base abstract class for IMetadataQueryExec implementations. Implementations of IMetadataQueryExec should
 * subclass this and implement executeQuery(Query queryObject, Map<String, Object> parameters and isLive()
 * 
 * @author James Dixon
 * 
 */
public abstract class BaseMetadataQueryExec implements IMetadataQueryExec {

  private boolean doQueryLog;

  private Integer maxRows;

  private Integer timeout;

  private boolean readOnly = true;

  private boolean forwardOnly = false;

  public IMetadataDomainRepository metadataDomainRepository;

  private IPhysicalModel physicalModel;

  protected Map<String, Property> parameters = new HashMap<String, Property>();

  protected Map<String, Property> inputs = new HashMap<String, Property>();

  public void setDoQueryLog( boolean doQueryLog ) {
    this.doQueryLog = doQueryLog;
  }

  public void setMaxRows( Integer maxRows ) {
    this.maxRows = maxRows;
  }

  public void setTimeout( Integer timeout ) {
    this.timeout = timeout;
  }

  public boolean getDoQueryLog() {
    return doQueryLog;
  }

  public Integer getMaxRows() {
    return maxRows;
  }

  public Integer getTimeout() {
    return timeout;
  }

  public IMetadataDomainRepository getMetadataDomainRepository() {
    return metadataDomainRepository;
  }

  public void setMetadataDomainRepository( IMetadataDomainRepository metadataDomainRepository ) {
    this.metadataDomainRepository = metadataDomainRepository;
  }

  public boolean isReadOnly() {
    return readOnly;
  }

  public void setReadOnly( boolean readOnly ) {
    this.readOnly = readOnly;
  }

  public boolean isForwardOnly() {
    return forwardOnly;
  }

  public void setForwardOnly( boolean forwardOnly ) {
    this.forwardOnly = forwardOnly;
  }

  public IPhysicalModel getPhysicalModel() {
    return physicalModel;
  }

  public void setPhysicalModel( IPhysicalModel physicalModel ) {
    this.physicalModel = physicalModel;
  }

  public void setParameter( Parameter param, Property value ) {

    if ( value != null && value.getValue() != null) {
      parameters.put( param.getName(), value );
    } else {
      parameters.put( param.getName(), param.getDefaultValue() );
    }
  }

  public void setInputs( Map<String, Property> inputs ) {
    this.inputs = inputs;
  }

  /**
   * Convert a parameter to it's expected query input type.
   * 
   * @param param
   *          the expected query parameter
   * @param parameters
   *          the list of inputs
   * 
   * @return the converted value
   */
  protected Property convertParameterValue( Parameter param, Property paramObj ) {
    if ( paramObj == null || paramObj.getValue() == null) {
      return null;
    }
    // convert the input parameter to the right parameter type
    switch ( param.getType() ) {
      case NUMERIC:
        if ( !( paramObj.getValue() instanceof Number ) ) {
          try {
            paramObj = new Property<Double>( Double.parseDouble( paramObj.getValue().toString() ) );
          } catch ( NumberFormatException e ) {
            // ignore failed conversion
          }
        }
        break;
      case BOOLEAN:
        if ( !( paramObj.getValue() instanceof Boolean ) ) {
          paramObj = new Property<Boolean>( Boolean.parseBoolean( paramObj.getValue().toString() ) );
        }
        break;
      case STRING:
        if ( !( paramObj.getValue() instanceof String ) ) {
          paramObj = new Property<String>( paramObj.getValue().toString() );
        }
        break;
    }
    return paramObj;
  }
}
