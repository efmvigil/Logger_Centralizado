# Logger Centralizado

## Visão Geral

Este projeto implementa um **sistema de logger centralizado, modular e extensível**, com suporte a múltiplos formatos de mensagem e múltiplos destinos de log. O logger permite que mensagens sejam compostas dinamicamente com funcionalidades adicionais e em formatos variados, permitindo que as mensagens sejam distribuídas para diferentes canais com diferentes necessidades, como console, arquivo, APIs remotas, etc.

## Padrões de Projeto Utilizados

### Singleton

O padrão Singleton foi utilizado para garantir que exista **uma única instância global do logger** durante toda a execução da aplicação. Isso evita a criação de múltiplas instâncias desnecessárias, que poderiam gerar conflitos ou inconsistências na emissão e distribuição dos logs. A centralização da responsabilidade de registro em uma única instância facilita o controle de configurações, evita redundâncias e proporciona um ponto único de acesso ao serviço de logging.

Este padrão foi implementado através da criação de uma classe Logger com um construtor privado e um método público getInstance que na primeira vez que é chamado cria uma nova instância de Logger, e em todas as chamadas subsequentes retorna a instância já criada ao invés de uma nova.

![singleton-no-codigo](screenshots/Singleton.png)

### Decorator

O padrão Decorator foi empregado para permitir a composição dinâmica de funcionalidades adicionais à mensagem de log sem alterar a lógica principal. Por meio dos decorators é possível adicionar elementos à mensagem de log como timestamps, níveis de severidade e formatações especiais (como em formato JSON para utilização por APIs), de forma modular e reaproveitável. Essa abordagem evita a proliferação de subclasses ou condicionais dentro do logger, permitindo criar cadeias de formatação específicas para diferentes destinos de log com facilidade.

Este padrão foi implementado no projeto com a criação da superclasse LogDecorator que é capaz de envolver uma classe que implementa a interface Log, e das suas subclasses que adicionam formatações específicas para o log envolvido sobrescrevendo o método getMessage de LogDecorator.

![log-decorator-no-codigo](screenshots/LogDecorator.png)

![exemplo-decorator-no-codigo](screenshots/TimestampDecorator.png)

### Observer

O padrão Observer foi utilizado para permitir que diversos componentes possam reagir a novos eventos de emissão de log. Isso permite, por exemplo, que uma mesma mensagem seja simultaneamente:

- exibida no console (para desenvolvedores)
- salva em um arquivo local (para auditoria)
- enviada para um servidor remoto (para monitoramento)
  
Com isso temos um sistema desacoplado, em que o logger principal não precisa conhecer a lógica interna de seus observadores. Novos destinos de log podem ser adicionados ou removidos sem impactar diretamente o resto do código, o que promove maior escalabilidade e facilidade na manutenção.

Este padrão foi utilizado no projeto a partir da criação da interface LogObserver que é implementada pelas diferentes classes de observadores, as quais podem ser adicionadas à lista de observadores armazenada na propriedade 'observers' do singleton de Logger, de forma que a cada emissão de log todos os observadores são notificados simultaneamente e distribuem o log para diversos destinos.

![log-observer-no-codigo](screenshots/LogObserver.png)

![exemplo-observer-no-codigo](screenshots/ConsoleLogObserver.png)