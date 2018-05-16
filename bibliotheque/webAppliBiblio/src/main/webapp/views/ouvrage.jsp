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
			
				<s:form action="ouvrage" method="post" theme="simple"
					cssClass="well form-inline">
					<s:textfield placeholder="Titre" name="ouvrage.titre" class="form-control" /> 
<%-- 					<s:select class="form-control" name="ouvrage.titre" list="ouvrages" --%>
<%-- 						listValue="titre" listKey="idOuvrage" />  --%>
					<s:textfield placeholder="Nom de l'auteur"
						name="auteur.nom" class="form-control" />
					<s:textfield placeholder="Isbn" name="ouvrage.isbn"
						class="form-control" />
<%-- 					<s:select class="form-control" list="#{'true':'Diponible', 'false':'Indisponible'}" headerKey="-1" headerValue="Choisir la disponibilité" --%>
<%-- 						name="ouvrage.disponibilite" value="" /> --%>
					<s:submit value="Rechercher" cssClass="btn btn-primary" />
					
				</s:form>
				<div class="tab-pane active" id="tab1" role="tabpanel">
					<s:actionmessage/>
				
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
										<s:a class="btn btn-primary" role="button" href="%{liendOu}">Voir détails ...</s:a>

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