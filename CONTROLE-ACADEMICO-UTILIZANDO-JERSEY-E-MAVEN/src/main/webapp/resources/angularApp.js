/*1 - Criaremos um módulo chamdo AngularApp que criamos na página Index.html.
 * 2 - Dentro do módulo "AngularApp" também vamos criar um controle que receberá o nome de AngularController.
 * 3 - O "AngularController" vai receber  um callback e dentro dessa função colocaremos o corpo do "controller"
 * */
angular.module("AngularApp", []).controller("AngularController", function(){
        var self = this;
        self.user = 'TesteMongoBom';
});