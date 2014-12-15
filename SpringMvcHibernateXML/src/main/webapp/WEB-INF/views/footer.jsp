<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//unslider.com/unslider.min.js"></script>

<script src="/spring/resources/js/ripples.min.js"></script>
<script src="/spring/resources/js/material.min.js"></script>
<script src="/spring/resources/js/star-rating.js"></script>
<script>
    $(document).ready(function() {
    	$(".mon_rating").rating({
        	clearCaption: "Pas encore noté",
        	starCaptions: {1: "Nul", 2: "Moyen", 3: "Bien", 4: "Excellent", 5: "Génial"}
        });
		
        $.material.init();
        
        $('.banner').unslider();
        
        
        
    });
    
    
</script>