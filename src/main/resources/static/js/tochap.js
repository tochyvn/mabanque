/**
 * 
 */


//$.noConflict();

$(document).ready(function() {
	
	var checkbox_virement = $("#virement"), compte_destinataire = $("#compte-destinataire");
	
	compte_destinataire.hide();
	
	$("input[name=typeOperation]:radio").change(function () {
		var selected = $(this).attr('id');
		if (selected == 'virement') {
			compte_destinataire.show();
		} else {
			compte_destinataire.hide();
		}
	});
	
});