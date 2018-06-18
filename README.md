# Trabalho final de Paradigmas de programação - Problema das crianças holandesas

## Problema inicial

O problema original se baseia em uma situação real onde crianças querem atravessar um rio por uma fita. Essa travessia, no entanto, só pode ocorrer de maneira unidirecional, ou seja, se uma ou mais crianças começam a passar pela fita do lado "A" para o lado "B", crianças do lado "B" devem esperar a fita ser desbloqueada.

Esse problema real tem o objetivo de simular a sincronização entre processos paralelos sendo executados e a troca de mensagens entre o processo que simula a fita ou ponte e os processos que são simulações das crianças.

No nosso trabalho, por motivos de simplicidade, apenas duas crianças foram utilizadas e a simulação da travessia só ocorre com uma criança de cada vez passando, isto é, duas crianças do mesmo lado não podem atravessa simultaneamente.

## Solução

Para solucionar o problemas utilizando o paradigma dos multi-agentes, foram utilizados um agente para cada criança e um para a ponte. O agente da criança utiliza o comportamento implementado pela classe FSMBehaviour da plataforma Jade que se baseia na criação de comportamentos utilizando uma máquina de estados finita. Nessa máquina de estados, as crianças estão brincando inicialmente. Por meio de um método que gera "aleatoriamente" um número entre 0 e 1, o estado se mantém em "PLAYING" ou "WANNA_CROSS", respectivamente. Ao mudar para "WANNA_CROSS", o agente da criança envia uma mensagem para ponte solicitando o acesso para a classe "CrossRequest", que herda da classe "CyclicBehaviour". Caso a ponte esteja ocupada, a criança recebe uma resposta de não. Caso contrário, o acesso é liberado e o estado da ponte é colocado em "busy". Ao terminar a travessia, a ponte é liberada pela classe "FinishRequest", que também se baseia em "CyclicBehaviour".

## Execução

Para executar o código, clone o repositório pelo comando `git clone https://github.com/ViniciusBernardo/jade-multi-agents`.

Após isso, siga o tutorial de instalação do Jade disponibilizado em PDF's na pasta doc.

Agora, no terminal:
```
cd bridgeOverRiver/src/
cJade bridge/*.java
cJade child/*.java
rJade -agents "bridge:bridge.BridgeAgent;child:child.ChildAgent"
```
