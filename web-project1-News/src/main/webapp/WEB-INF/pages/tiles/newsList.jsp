<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localizationGeneral.jsp" %>

<div class="body-title">
	<a href="controller?command=go_to_news_list">${news_} </a> ${news_list_b}
	<br /> <br /> <br /> <br />
	</div>

<form action="controller" method="post">
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<c:out value="${news.newsDate}" />
				</div>

				<div class="news-content">
					<c:out value="${news.briefNews}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
						      <a href="controller?command=go_to_edit_news&id=${news.idNews}"> ${editlink} </a> 
						</c:if>
						
						<a href="controller?command=go_to_view_news&id=${news.idNews}"> ${viewlink} </a> 
   					    
   					    <c:if test="${sessionScope.role eq 'admin'}">
   					         <input type="checkbox" name="id" value="${news.idNews}" />
   					    </c:if>
					</div>
				</div>

			</div>
		</div>

	</c:forEach>
	 
	 	
	<!-- 	<logic:notEmpty name="newsForm" property="newsList">
		<div class="delete-button-position">
			<html:submit>
				<bean:message key="locale.newslink.deletebutton" />
			</html:submit>
		</div>
	</logic:notEmpty> -->

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        ${no_news}
	</c:if>
	</div>

	<c:if test="${sessionScope.role eq 'admin'}">
		<div class="first-view-button">
			<input type="hidden" name="command" value="do_delete_news" /> 
			<input type="submit" value="${delete}" />
	</div>
	</c:if>	
</form>