<div class="navbar navbar-warning">
	<div class="navbar-header"><a class="navbar-brand"><i class="mdi-image-audiotrack"></i>Muzik Kloud</a></div>
	<div class="navbar-collapse collapse navbar-warning-collapse">
		<ul class="nav navbar-nav">
			<li><a href="/spring/">Accueil</a></li>
			<li><a href="/spring/admin/artiste">Artistes</a></li>
			<li><a href="/spring/admin/genre">Genres</a></li>
			<li><a href="/spring/admin/album">Albums</a></li>
			<li><a href="/spring/admin/musique">Musiques</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li>
				<% if(request.getSession().getAttribute("logged")!=null){ %>
				<a href="/spring/logout"><i class="mdi-navigation-cancel"></i> Deconnexion</a>
				<% } %>
			</li>
		</ul>
	</div>
</div>

