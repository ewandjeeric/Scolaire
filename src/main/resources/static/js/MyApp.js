var MyApp = angular.module("MyApp", []);
MyApp.controller("InscriptionController", function($scope, $http){
	$scope.etudiant={};
	$scope.errors=null;
	$scope.form = false;
	

	
	$scope.saveEtudiant=function(){
		$http.post("etudiants",$scope.etudiant).success(function(data){
			if(!data.errors){
				$scope.etudiant = data;
				$scope.errors = null;
				$scope.form = true;
				
			}
			else{
				$scope.errors=data;
				$scope.form = false;
			}
		});
	};

});
MyApp.controller("listeEtudiantController", function($scope, $http){
	
	$scope.pageEtudiants=null;
	$scope.pageCourante="0";
	$scope.size="5";
	$scope.listeEtudiants=function(){
	$http.get("etudiants?page="+$scope.pageCourante+"&size="+$scope.size).success(function(data){
		$scope.pageEtudiants=data;
	});
}
$scope.listeEtudiants();

});

MyApp.controller("IndexController", function($scope, $http){
	$scope.menu=["Inscription", "Listes", "Utilisateurs"];
	$scope.selectMenu=null;
	$scope.selection=function(m){
		$scope.selectMenu=m;
	};
});