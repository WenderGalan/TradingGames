Integrantes do grupo: -Wender Galan Benage RGA.: 2015.1902.042-2

                  -Matheus Vinicius Pimentel Cabreira     RGA.: 2015.1902.016-3
	      
	      -João Vitor de Souza Queiroz	      RGA.: 2015.1902.020-1 
Link para o vídeo no youtube (NÃO LISTADO):

A pasta "android" terá os arquivos do projeto mobile já a pasta "server" terá o projeto do servidor, o qual será feito push no repositório abaixo (link) e depois trazido para cá. Link: https://github.com/NordicPlayer2/tradinggames-api

Pois precisa efetuar testes com o servidor e fazer o deploy no heroku, então somente será feito um push inicial do servidor aqui e depois o push final com o servidor pronto. O histórico de modificações do servidor se encontra no repositório acima.

O servidor foi feito em java, com o framework Spring boot, o servidor e o banco ficarão online para acesso da aplicação.

Manual de utilização do aplicativo:

1° - Logo na tela inicial é obrigatório o cadastro de um usuário para ter acesso a aplicação, após criar um login e senha pode-se logar normalmente, pode demorar alguns segundos para a API responder devido ela estar "dormindo" quando não usada mas depois será mais rápido. O botão "esqueceu sua senha?" foi implementado apenas a activity mas infelizmente não deu tempo de fazer a implementação da classe para enviar email confirmando mesmo a identidade do usuario para poder redefinir a senha, mas temos um modelo utilizando um número Random que seria enviado para o e-mail do usuário validando o mesmo e dando acesso para a redefinição da senha.

2° - Após o Login é possível visualizar os anúncios na tela inicial, clicando em cada anúncio é possível ver a descrição completa do mesmo, assim como o nome do Autor do anúncio, informações da categoria do anúncio e etc.

3° - Na mesma tela dos anúncios temos acesso a outras interfaces, como por exemplo o botão flutuante para acesso a criação de um novo anúncio, clicando e nele e preenchendo as informações é possível inserir esse anúncio, que logo depois estará disponível na tela inicial, as tags pedidas no final de cada inserção é para controle dentro do banco de dados para numa eventual atualização do app e inserção de filtros no aplicativo.

4° - Acima da tela principal de anúncios temos acesso a deslogar da conta, infelizmente não tivemos tempo para trabalhar com banco de dados do próprio android para salvar as credenciais do usuário e manter logado, mas com este botão é possível sair para a tela inicial de Login.

5° - Meu Usuário - também é possível acessar as informações do usuário e altera-las.

6° - Meus Anúncios - É possível acessar os seus anúncios, mas ainda não foi implementado, apenas adicionado o botão.

7° - Anuncios - Clicando neste botão você será redirecionado a página de anuncios.

Observações: A idéia do app era algo parecido com isso, mas devido ao pouco tempo disponível no semestre e a alguns erros que surgiram durante o desenvolvimento, algumas coisas ficaram a desejar como ficou explicito acima.

Bugs:

Infelizmente devido também ao tempo curto, alguns bugs não serão consertados antes da entrega, o bugs mais evidente é em relação ao List View, quando entramos na parte de anúncio e requisitamos a imagem pela URL da mesma ele esta baixando a imagem mas esta bagunçado sua ordem, depois de olhar e analisar aquilo, notamos que iria precisar de uma fila de requisições para que fosse requisitado e atendido de forma linear, mas não houve tempo para a implementação.
Dentro do detalhe de cada anúncio a ideia seria passar um slide de imagens porque no cadastro do anúncio podemos adicionar até 6 imagens, dessa forma iria exibir todas as 6 nos detalhes, mas devido ao pouco tempo e também a pouca quantidade de informação em relação a biblioteca ImageSlider na internet não conseguimos implementar aquilo de forma adequada.
