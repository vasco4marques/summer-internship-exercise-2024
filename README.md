# Solução para o problema: Teknonymy - Summer Internship Exercise

## O Problema
Teknonymy = tecnónimo

Em algumas sociedades, recebemos um nome próprio no nosso nascimento e as pessoas usam-no para se referir a nós enquanto não temos filhos. Depois de termos pelo menos um filho, as pessoas colocam-nos um tecnónimo. Um tecnónimo é uma forma de se referir a alguém de acordo com os seus descendentes, como 'Catarina mãe de João' ou 'Pedro bisavô de Joana”, etc. 

Neste exercício receberás uma espécie de árvore genealógica de uma família e a tua tarefa será dar o tecnónimo correto, de acordo com as regras especificadas na próxima seção. 

## Regras

* Se X não tem descendentes diretos, então X não têm tecnónimo (isto é, o retorno é ""). 
* Um descendente direto de X é qualquer membro, exceto X, da subárvore com raiz em X. 
* Se X tem alguns descendentes diretos: o tecnónimo é construído de acordo com o sexo de X ('M' -> male , 'F' -> female) e de acordo o nome do mais velho entre os seus descendentes diretos da geração mais distante de X. 
* A “distância de geração” entre X e um dos seus descendentes, digamos Y, é o número de níveis entre X e Y. 
* Os tecnónimos possíveis são (todos em minúsculas): 'mother of Y', 'grandfather of Y', 'great-grandmother of Y', 'great-great-grandmother of Y', e assim por diante. 
* Garantimos que não haverá ambiguidade para determinar quem é o mais velho entre dois descendentes diretos de alguém.
 

 ## Exemplo


![alt text](image.png?raw=true)

Para A: a última geração, e a mais distante de A, é a terceira geração, o mais velho dessa geração é F e há duas gerações entre A e F, então o tecnónimo de A é 'grandfather of F'; 

para B: B tem apenas um descendente direto, E, na terceira geração, e há uma geração entre B e E, então o tecnónimo  de B é 'father of E'; 

para D: D tem três descendentes diretos na terceira geração, F, G e H, sendo F o mais velho, então o tecnónimo de D é 'mother of F'; 

C, H, E, F e G não têm descendente, portanto não possuem tecnónimo. 

## O que é preciso fazer?

* Implementar o método `getTeknonymy` em `TeknonymyService`.
* Implementar mais testes em `TeknonymyServiceTest`

Vai ser valorizado a performance tanto para árvores geneológicas grandes como para pequenas, assim como o facto do código estar escrito de forma perceptível e organizada.

## Como correr os testes

* Instalar o mvn [https://maven.apache.org/install.html]
* Correr `mvn test`

## Dúvidas

Quaisquer dúvidas que tenhas, cria um issue aqui no github :)

