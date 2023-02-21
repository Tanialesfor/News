<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localizationGeneral.jsp" %>

<div class="wrapper">
	<div class="newstitle"> ${news_management}</div>

	<div class="local-link">

		<div align="right">
			<a href="controller?command=do_localization&local=en"> 
				<c:out value="${en_button}" /> 
			</a> &nbsp;&nbsp; 
			<a	href="controller?command=do_localization&local=ru"> 
				<c:out value="${ru_button}" /> 
			</a> <br /> <br />
		</div>
	
		<c:if test="${not (sessionScope.user eq 'active')}">
			<div align="right">
				<form action="controller" method="post">
									  <input type="hidden" name="command" value="do_sign_in" /> 
					${login}    <input type="text" name="login" value="" title="symbols from 1 to 10"/><br /> 
					${password} <input type="password" name="password" value="" title="symbols from 1 to 10"/><br />
	
					<c:if test="${not (requestScope.AuthenticationError eq null)}">
						<font color="red"> 
						   <c:out value="${signIn_auther_error_text}" />
						   </font> 
						 </c:if>	  
				  
				  <c:if test="${not (requestScope.RegistrationError eq null)}">
						<font color="red"> 
						   <c:out value="${doRegistration_auther_inf_text}" />
						   <c:remove var="RegistrationError"/>	
						</font> 
					</c:if>	
					
					<c:if test="${not (sessionScope.autherMessageReg eq null)}">
						<font color="orange"> 
						   	<c:out value="${doRegistration_auther_message_text}" />
						   	<c:remove var="autherMessageReg"/>						   	
						</font> 
					</c:if>		
					
					<c:if test="${not (sessionScope.autherInfReg eq null)}">
						<font color="red"> 
						   	<c:out value="${doRegistration_auther_inf_text}" />
						   	<c:remove var="autherInfReg"/>						   	
						</font> 
					</c:if>	
					
					<c:if test="${not (sessionScope.autherMessage eq null)}">
						<font color="blue"> 
					   		<c:out value="${sessionScope.autherMessage}" />
						</font> 
					</c:if>	
																								
					<a href="controller?command=go_to_registration_page"> ${registration} </a> 
					<input type="submit" value="${sign_In}" /><br />
				</form>
			</div>
		</c:if>
		
							
		<c:if test="${sessionScope.user eq 'active'}">
			<div align="right">
            	<form action="controller" method="post">
	            	<c:if test="${not (sessionScope.autherMessage eq null)}">
						<font color="blue"> 
						   	<c:out value="${sessionScope.autherMessage}" />
						   	<c:remove var="autherMessage"/>						   	
						</font> 
					</c:if>	
						
																														
   					<input type="hidden" name="command" value="do_sign_out" /> 
					<input type="submit" value="${sign_Out}" /><br />
				</form>					
			</div>
		</c:if>				
	</div>
</div>
