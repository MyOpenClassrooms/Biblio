<%@ include file="taglib.jsp"%>

<body>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="panel panel-default centre">
			<div class="panel-heading">
				<h4>
					<b>Détail de l'ouvrage: "<s:property value="ouvrage.titre" />"
					</b>
				</h4>
			</div>

			<div class="panel-body">

				<div class="row">

					<div class="col-md-6 text-left ">
						<h3 class="top_title" style=""></h3>
						<span class="para_text" style=""> Ecrit par <b><s:property
									value="ouvrage.auteur.nom" /> <s:property
									value="ouvrage.auteur.prenom" /></b> <br /> <br />
									Resumé de l'ouvrage:
							<b><p>
									<s:property value="ouvrage.resume" />
								</p> </b><br /> <br /> Nombre de pages: <b><s:property
									value="ouvrage.nbrpage" />  </b> pages<br />
									Disponiblité: <b><s:if test="%{ouvrage.disponibilite==false}">
											Indisponible
										</s:if> <s:else>
										    Disponible
										</s:else></b>
									<br />
									Catégorie: <b><s:property value="ouvrage.categorie.libelle" /></b><br />
									Nombre d'exemplaire: <b><s:property value="exemplaire.nbrExemplaire" /></b><br />
									<s:if test="%{ouvrage.disponibilite==false}">Le nombre de personnes ayant réservé l’ouvrage: <b><s:property value="reservations.size()" /></b><br />
									Date de retour prévue: <b><s:date name="dateRetourPrevu.toGregorianCalendar()" format="dd-MM-yyyy" /></b></s:if>
									<section style="margin-top: 75px">
										<p>
											<a href="<s:url value="/ouvrage"/>" class="btn btn-primary"
												role="button">Retour</a>
												<s:if test="%{ouvrage.disponibilite==false}">
											<s:a class="btn btn-primary left" role="button"
												href="%{lienResa}">Réserver</s:a>
										</s:if>
										</p>
									</section>
					</div>
					
				</div>

			</div>
		</div>
	</div>
</body>
</html>