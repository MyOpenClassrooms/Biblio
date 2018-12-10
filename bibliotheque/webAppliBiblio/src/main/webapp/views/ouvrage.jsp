<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="taglib.jsp"%>

<body>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="panel panel-default centre">
			<div class="panel-heading">
				<h4>
					<b>La liste des Ouvrages</b>
				</h4>
			</div>
			<div class="panel-body">
				<s:if test="hasActionErrors()">
					<div class="errors">
						<s:actionerror />
					</div>
				</s:if>
				<s:if test="hasActionMessages()">
					<div class="welcome">
						<s:actionmessage />
					</div>
				</s:if>
				<s:form action="ouvrage" method="post" theme="simple"
					cssClass="well form-inline">
					<s:textfield placeholder="Titre" name="ouvrage.titre"
						class="form-control" />
					<%-- 					<s:select class="form-control" name="ouvrage.titre" list="ouvrages" --%>
					<%-- 						listValue="titre" listKey="idOuvrage" />  --%>
					<s:textfield placeholder="Nom de l'auteur" name="auteur.nom"
						class="form-control" />
					<s:textfield placeholder="Isbn" name="ouvrage.isbn"
						class="form-control" />
					<%-- 					<s:select class="form-control" --%>
					<%-- 						list="#{'true':'Diponible', 'false':'Indisponible'}" --%>
					<%-- 						headerKey="-1" headerValue="Choisir la disponibilité" --%>
					<%-- 						name="ouvrage.disponibilite" value="null" /> --%>
					<s:radio class="radio-inline" name="ouvrage.disponibilite"
						list="#{'true' : 'Diponible'}" />
					<s:radio class="radio-inline" name="ouvrage.disponibilite"
						list="#{ 'false' : 'Indisponible'}" />
					<s:submit value="Rechercher" cssClass="btn btn-primary" />


				</s:form>
				<%-- 				<s:submit value="Rafléchir" cssClass="btn btn-primary" /> --%>
				<div class="tab-pane active" id="tab1" role="tabpanel">
					<%-- 					<s:actionmessage /> --%>

					<s:iterator value="ouvrages">

						<div class="col-sm-3">
							<div class="thumbnail">
								<!--                         <img alt="..." src="http://placehold.it/240x150"> -->
								<div class="caption">
									<h3>
										<s:property value="titre" />
									</h3>
									<p>
										<s:property value="resume" />
									</p>
									<p>
										<s:url namespace="/" action="detailsOuvrage" var="liendOu">
											<s:param name="ouvrage.idOuvrage" value="idOuvrage" />
										</s:url>
										<s:a class="btn btn-primary" role="button" href="%{liendOu}">Voir détails</s:a>
										<s:url namespace="/" action="reserver" var="lienResa">
											<s:param name="ouvrage.idOuvrage" value="idOuvrage" />
											<s:param name="utilisateur.idUser"
												value="#session.utilisateur.idUser" />
										</s:url>

										<s:if test="%{disponibilite==false}">
											<s:a class="btn btn-primary left" role="button"
												href="%{lienResa}">Réserver</s:a>
										</s:if>

									</p>
								</div>
							</div>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>

	</div>
</body>
</html>