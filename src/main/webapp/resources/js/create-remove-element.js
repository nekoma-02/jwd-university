$(document).ready(
		function($) {

			$('.plus-spec').click(function(){

				$(this).before($(".subject_json_plus:last").clone().css('display','inline').removeClass().append('<span class="btn btn-danger minus pull-right">-</span>'));
		 
				});
			
			$('.plus').click(function(){

				$(this).before($(".subject_json_plus:last").clone().css('display','inline').removeClass().append('<span class="btn btn-danger minus pull-right">-</span>'));
		 
				});
			
			
			$(document).on('click', '.minus', function(){
				 $(this).closest('div').remove();
				});		

		});
