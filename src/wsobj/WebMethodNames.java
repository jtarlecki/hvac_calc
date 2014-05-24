package wsobj;

public final class WebMethodNames
{
	public static final String endpointurl = "https://ws3.realwinwin.local:8443/RealWinWinUI/services/";
	//public static final String endpointurl = "https://ws3.realwinwin.local:8443/RealWinWinUI_DEV/services/";
	/*
	 * Web Service: OEMWebMethod
	 *  <element name="OEMsXML">
    <complexType/>
   </element>
   <element name="OEMsXMLResponse">
    <complexType>
     <sequence>
      <element name="OEMsXMLReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String oem_webservice = "OEMWebMethod";
	public static final String oem_opt_OEMsXML = "OEMsXML";
	
	/*
	 *  Web Service: EmployeeWebMethod
	 *  <wsdl:operation name="getLoginEmployeeInfo">

         <wsdlsoap:operation soapAction=""/>

         <wsdl:input name="getLoginEmployeeInfoRequest">

            <wsdlsoap:body use="literal"/>

         </wsdl:input>

         <wsdl:output name="getLoginEmployeeInfoResponse">

            <wsdlsoap:body use="literal"/>

         </wsdl:output>

      </wsdl:operation>

      <wsdl:operation name="getLoginEmployeeId">

         <wsdlsoap:operation soapAction=""/>

         <wsdl:input name="getLoginEmployeeIdRequest">

            <wsdlsoap:body use="literal"/>

         </wsdl:input>

         <wsdl:output name="getLoginEmployeeIdResponse">

            <wsdlsoap:body use="literal"/>

         </wsdl:output>

      </wsdl:operation>
	 */
	public static final String emp_webservice = "EmployeeWebMethod";
	public static final String emp_opt_getLoginEmployeeId = "getLoginEmployeeId";
	public static final String emp_opt_getLoginEmployeeInfo = "getLoginEmployeeInfo";
	public static final String emp_getEmployeesByEmployeeType = "getEmployeesByEmployeeType";
	public static final String emp_getAllEmployees = "getAllEmployees";
	
	/*
	 * Web Service: ToolbarWebMethod
	 *  <element name="hasMenuPermission">
    <complexType>
     <sequence>
      <element name="empid" type="xsd:long"/>
      <element name="menuid" type="xsd:int"/>
     </sequence>
    </complexType>
   </element>
   <element name="hasMenuPermissionResponse">
    <complexType>
     <sequence>
      <element name="hasMenuPermissionReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="loadToolbarItems">
    <complexType>
     <sequence>
      <element name="empid" type="xsd:long"/>
      <element name="menuid" type="xsd:int"/>
     </sequence>
    </complexType>
   </element>
   <element name="loadToolbarItemsResponse">
    <complexType>
     <sequence>
      <element name="loadToolbarItemsReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="hasURLPermission">
    <complexType>
     <sequence>
      <element name="empid" type="xsd:long"/>
      <element name="menuid" type="xsd:long"/>
      <element name="urladdress" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="hasURLPermissionResponse">
    <complexType>
     <sequence>
      <element name="hasURLPermissionReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String toolbaritem_webservice = "ToolbarWebMethod";
	public static final String toolbaritem_opt_hasMenuPermission = "hasMenuPermission";
	public static final String toolbaritem_opt_loadToolbarItems = "loadToolbarItems";
	public static final String toolbaritem_opt_hasURLPermission = "hasURLPermission";
	
	/*
	 * Web Service: EquipmentWebMethod
	 * <element name="loadEquipmentByOEMAndZip">
    <complexType>
     <sequence>
      <element name="zipcodes" type="xsd:string"/>
      <element name="oems" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="loadEquipmentByOEMAndZipResponse">
    <complexType>
     <sequence>
      <element name="loadEquipmentByOEMAndZipReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="loadEquipmentByTrade">
    <complexType>
     <sequence>
      <element name="tradenames" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="loadEquipmentByTradeResponse">
    <complexType>
     <sequence>
      <element name="loadEquipmentByTradeReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="loadEquipmentByOEM">
    <complexType>
     <sequence>
      <element name="oems" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="loadEquipmentByOEMResponse">
    <complexType>
     <sequence>
      <element name="loadEquipmentByOEMReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String equipment_webservice = "EquipmentWebMethod";
	public static final String equipment_loadEquipmentByOEM = "loadEquipmentByOEM";
	public static final String equipment_loadEquipmentByOEMAndZip = "loadEquipmentByOEMAndZip";
	public static final String equipment_loadEquipmentByTrade = "loadEquipmentByTrade";
	
	/*
	 * web service: UtilityWebMethod
	 * 
	 * <element name="loadUtiltiyNames">
    <complexType/>
   </element>
   <element name="loadUtiltiyNamesResponse">
    <complexType>
     <sequence>
      <element name="loadUtiltiyNamesReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="loadUtilityByZipCode">
    <complexType>
     <sequence>
      <element name="zipcode" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="loadUtilityByZipCodeResponse">
    <complexType>
     <sequence>
      <element name="loadUtilityByZipCodeReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String utility_webservice = "UtilityWebMethod";
	public static final String utility_loadUtiltiyNames = "loadUtiltiyNames";
	public static final String utility_loadUtilityByZipCode = "loadUtilityByZipCode";
	
	/*
	 * web service: HVACCalcResultWebMethod
	 * 
	 * <element name="calculateCustomHVACWithEquipments">
    <complexType>
     <sequence>
      <element name="ziputilities" type="xsd:string"/>
      <element name="utilities" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="calculateCustomHVACWithEquipmentsResponse">
    <complexType>
     <sequence>
      <element name="calculateCustomHVACWithEquipmentsReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="calculateCustomHVAC">
    <complexType>
     <sequence>
      <element name="hvacresultxml" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="calculateCustomHVACResponse">
    <complexType>
     <sequence>
      <element name="calculateCustomHVACReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="calculateHVAC">
    <complexType>
     <sequence>
      <element name="hvacresultxml" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="calculateHVACResponse">
    <complexType>
     <sequence>
      <element name="calculateHVACReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String hvaccalc_webservice = "HVACCalcResultWebMethod";
	public static final String hvaccalc_calculateHVAC = "calculateHVAC";
	public static final String hvaccalc_calculateCustomHVAC = "calculateCustomHVAC";
	public static final String hvaccalc_calculateCustomHVACWithEquipments = "calculateCustomHVACWithEquipments";
	public static final String hvaccalc_estimateCustomHVACWithEquipments = "estimateCustomHVACWithEquipments";
	public static final String hvaccalc_submitCustomRebatesReview = "submitCustomRebatesReview";
	
	/*
	 * Web service: BuildingTypesWebMethod
	 * 
	 *  <element name="getBuildingTypes">
    <complexType/>
   </element>
   <element name="getBuildingTypesResponse">
    <complexType>
     <sequence>
      <element name="getBuildingTypesReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String buildingtypes_webservice = "BuildingTypesWebMethod";
	public static final String buildingtypes_getBuildingTypes = "getBuildingTypes";
	
	/*
	 * Web service: ProjectTypesWebMethod
	 * 
	 * <element name="getProjectTypes">
    <complexType/>
   </element>
   <element name="getProjectTypesResponse">
    <complexType>
     <sequence>
      <element name="getProjectTypesReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String projecttypes_webservice = "ProjectTypesWebMethod";
	public static final String projecttypes_getProjectTypes="getProjectTypes";
	
	/*
	 * Web service: ReplacingEquipmentWebMethod
	 * 
	 *  <element name="getReplacingEquipments">
    <complexType/>
   </element>
   <element name="getReplacingEquipmentsResponse">
    <complexType>
     <sequence>
      <element name="getReplacingEquipmentsReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String replacingequipment_webservice = "ReplacingEquipmentWebMethod";
	public static final String replacingequipment_getReplacingEquipments = "getReplacingEquipments";
	
	/*
	 * 
	 * Web Service: 
	 * 
	 *  <element name="submitProject">
    <complexType>
     <sequence>
      <element name="username" type="xsd:string"/>
      <element name="companyname" type="xsd:string"/>
      <element name="phonenumber" type="xsd:string"/>
      <element name="emailaddr" type="xsd:string"/>
      <element name="projectname" type="xsd:string"/>
      <element name="street" type="xsd:string"/>
      <element name="city" type="xsd:string"/>
      <element name="state" type="xsd:string"/>
      <element name="zipcode" type="xsd:string"/>
      <element name="utility" type="xsd:string"/>
      <element name="buildingtype" type="xsd:string"/>
      <element name="buildingsqft" type="xsd:string"/>
      <element name="operatinghours" type="xsd:string"/>
      <element name="projecttype" type="xsd:string"/>
      <element name="units" type="xsd:string"/>
      <element name="installdate" type="xsd:string"/>
      <element name="summaryscope" type="xsd:string"/>
      <element name="additinalnotes" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="submitProjectResponse">
    <complexType>
     <sequence>
      <element name="submitProjectReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String project_webservice = "ProjectWebMethod";
	public static final String project_submitProject = "submitProject";
	public static final String project_submitprojectcustominquiry = "submitProjectCustomInquiry";
	
	/*
	 * Request Session Web services: RequestSessionWebMethod
	 * 
	 *   <complexType>
     <sequence>
      <element name="itemxml" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="insertRequestSessionResponse">
    <complexType>
     <sequence>
      <element name="insertRequestSessionReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="RequestSessionLoggedOut">
    <complexType>
     <sequence>
      <element name="itemxml" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="RequestSessionLoggedOutResponse">
    <complexType>
     <sequence>
      <element name="RequestSessionLoggedOutReturn" type="xsd:string"/>
     </sequence>
    </complexType>
	 */
	public static final String requestsession_webservice = "RequestSessionWebMethod";
	public static final String requestsession_insertRequestSession = "insertRequestSession";
	public static final String requestsession_LoggedOut = "requestSessionLoggedOut";
	
	/*
	 * Web service: EquipmentTypesWebMethod
	 * 
	 *  <element name="getEquipmentTypes">
    <complexType/>
   </element>
   <element name="getEquipmentTypesResponse">
    <complexType>
     <sequence>
      <element name="getEquipmentTypesReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String equipmenttypes_webservice = "EquipmentTypesWebMethod";
	public static final String equipmenttypes_getEquipmentTypes = "getEquipmentTypes";
	
	/*
	 * Web Service: SubEquipmentTypesWebMethod
	 * 
	 *  <element name="getSubEquipmentTypesByEquipment">
    <complexType>
     <sequence>
      <element name="equipment" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="getSubEquipmentTypesByEquipmentResponse">
    <complexType>
     <sequence>
      <element name="getSubEquipmentTypesByEquipmentReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String subequipmenttypes_webservice = "SubEquipmentTypesWebMethod";
	public static final String subequipmenttypes_getSubEquipmentTypesByEquipment = "getSubEquipmentTypesByEquipment";
	
	/*
	 * Web Service: UOMWebMethod
	 * 
	 *  <element name="getUOMsByTypes">
    <complexType>
     <sequence>
      <element name="types" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
   <element name="getUOMsByTypesResponse">
    <complexType>
     <sequence>
      <element name="getUOMsByTypesReturn" type="xsd:string"/>
     </sequence>
    </complexType>
   </element>
	 */
	public static final String uom_webservice = "UOMWebMethod";
	public static final String uom_getUOMsByTypes = "getUOMsByTypes";
}
