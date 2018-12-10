<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="taglib.jsp"%>
<body>

	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="panel panel-default centre">
			<div class="panel-heading">
				<h4>
					<b>La liste des réservations</b>
				</h4>
			</div>
			<div class="panel-body">
				<s:actionerror />
				<s:actionmessage />
				<table class="table table-striped custab">
					<thead>
						<tr>
							<th>ID du réservation</th>
							<th>Ouvrage</th>
							<th>Date retour plus proche</th>
							<th>Position</th>
<!-- 							<th>User</th> -->
							<th class="text-center">Action</th>
						</tr>
					</thead>
<%-- 					<s:iterator var="i" step="1" value="dates"> --%>
<%-- 					<s:property></s:property> --%>
					
					<s:iterator value="reservations">
<%-- 			 <s:iterator var="i" step="1" value="dates"> --%>
   
  
						<tr>
							<td><s:property value="idResa" /></td>
							<td><s:property value="ouvrage.titre" /></td>
							
 							<td>
 							<s:if test="%{dateRetourPlusProche==null}">
											Non déterminée
										</s:if> 
										<s:else>
										    <s:date name="dateRetourPlusProche.toGregorianCalendar()" format="dd-MM-yyyy" />
										</s:else>
 							
 							</td> 
 							
 							
<%--      <td><s:property></s:property></td>  --%>
							<td><s:property value="position" /></td>
<%-- 							<td><s:property value="user.idUser" /></td> --%>

							<td class="text-center"><s:url namespace="/"
									action="annuler" var="lienAnnulResa">
									<s:param name="reservation.idResa" value="idResa" />
									<s:param name="idOuvrage" value="ouvrage.idOuvrage" />
								</s:url> <s:a class='btn btn-info btn-xs' href="%{lienAnnulResa}">
									<span class="glyphicon glyphicon-edit"></span>Annuler</s:a></td>
						</tr>
						</s:iterator>
<%-- 						</s:iterator> --%>
<%-- 					</s:iterator> --%>
				</table>
			</div>
		</div>
	</div>
</body>
</html>