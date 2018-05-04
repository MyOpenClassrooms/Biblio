<%@ include file="taglib.jsp"%>
<body>

	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="panel panel-default centre">
			<div class="panel-heading">
				<h4>
					<b>La liste des prets</b>
				</h4>
			</div>
			<div class="panel-body">
				<s:actionerror />
			<s:actionmessage/>
				<table class="table table-striped custab">
					<thead>
						<a href="#" class="btn btn-primary btn-xs pull-right"><b>+</b>
							Ajouter un pret</a>
						<tr>
							<th>ID du pret</th>
							<th>Ouvrage</th>
							<th>Date de sortie</th>
							<th>Date de retour</th>
							<th>Status</th>
							<th class="text-center">Action</th>
						</tr>
					</thead>
					<s:iterator value="prets">
						<tr>
							<td><s:property value="idPret" /></td>
							<td><s:property value="exemplaire.ouvrage.titre" /></td>
							<td><s:date name="dateSortie.toGregorianCalendar()" format="dd-MM-yyyy"/></td>
							<td><s:date name="dateRetourPrevu.toGregorianCalendar()" format="dd-MM-yyyy" /></td>
							<td>
								<s:if test="%{rendu==false}">
											Non rendu
										</s:if> <s:else>
										    Rendu
										</s:else>
							</td>
							<td class="text-center">
							<s:url namespace="/" action="prolonger" var="lienProlonger">
							<s:param name="dateRetourPrevu" value="dateRetourPrevu"/></s:url>
					<s:a class='btn btn-info btn-xs' href="%{lienProlonger}"><span class="glyphicon glyphicon-edit"></span>Prolonger</s:a>
							</td>
						      </tr>
					</s:iterator>
				</table>
			</div>
		</div>
	</div>
</body>
</html>