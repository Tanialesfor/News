<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localizationGeneral.jsp" %>

<div class="body-title">
	<a href="controller?command=go_to_news_list"> ${news_} </a> ${add_news_b}
	
</div>

<div class="add-table-margin">
<form action="controller" method="post">
			
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${news_title}</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				 <input type="text" name="title" placeholder="enter title of news" value="${requestScope.news.title }" > 
			</div></td>
			
		</tr>
		<tr>
			<td class="space_around_title_text">${news_date}</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<input type="text" name="date" placeholder="template YYYY-MM-DD" value="${requestScope.news.newsDate }">
			</div></td>
				
		</tr>
		<tr>
			<td class="space_around_title_text">${brief}</td>
			<td class="space_around_view_text">
			<div class="word-area-breaker">
			
			<textarea rows="8" cols="50" name="brief"  title="enter brief of news"> 
			<c:out value="${requestScope.news.briefNews }" />
			</textarea>
			</div></td>
			
		</tr>
		<tr>
			<td class="space_around_title_text">${content}</td>
			<td class="space_around_view_text">
			<div class="word-area-breaker">
			
			<textarea rows="12" cols="50" name="content"  title="enter content of news"> 
			<c:out value="${requestScope.news.content }" />
			</textarea>
			</div></td>
			
		</tr>
	</table>
	<c:if test="${sessionScope.role eq 'admin'}">
	<div class="first-view-button">
			<input type="hidden" name="command" value="do_add_news" /> 
			<input type="hidden" name="id" value="${requestScope.news.idNews}" /> 
			<input type="submit" value="${save}" />
		</div>
	</c:if>
	</form>
	
	<div class="second-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_news_list" /> 
			<input type="hidden" name="id" value="${requestScope.news.idNews}" /> 
			<input type="submit" value="${cancel}" />
		</form>
	</div>
	
</div>


