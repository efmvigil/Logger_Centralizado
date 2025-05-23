# Logger Centralizado
## Visão Geral
Este projeto implementa um **sistema de logger centralizado, modular e extensível**, com suporte a múltiplos formatos de mensagem e múltiplos destinos de log. O logger permite que mensagens sejam compostas dinamicamente com funcionalidades adicionais e em formatos variados, permitindo que as mensagens sejam distribuídas para diferentes canais com diferentes necessidades, como console, arquivo, APIs remotas, etc.

## Padrões de Projeto Utilizados
### Singleton
O padrão Singleton foi utilizado para garantir que exista **uma única instância global do logger** durante toda a execução da aplicação. Isso evita a criação de múltiplas instâncias desnecessárias, que poderiam gerar conflitos ou inconsistências na emissão e distribuição dos logs. A centralização da responsabilidadede registro em uma única instância facilita o controle de configurações, evita redundâncias e proporciona um ponto único de acesso ao serviço de logging.

### Decorator
O padrão Decorator foi empregado para permitir a composição dinâmica de funcionalidades adicionais à mensagem de log sem alterar a lógica principal. Por meio dos decorators é possível adicionar elementos à mensagem de log como timestamps, níveis de severidade e formatações especiais (como em formato JSON para utilização por APIs), de forma modular e reaproveitável. Essa abordagem evita a proliferação de subclasses ou condicionais dentro do logger, permitindo criar cadeias de formatação específicas para diferentes destinos de log com facilidade.

### Observer
O padrão Observer foi utilizado para permitir que diversos componentes possam reagir a novos eventos de emissão de log. Isso permite, por exemplo, que uma mesma mensagem seja simultaneamente:
- exibida no console (para desenvolvedores)
- salva em um arquivo local (para auditoria)
- enviada para um servidor remoto (para monitoramento)
Com isso temos um sistema desacoplado, em que o logger principal não precisa conhecer a lógica interna de seus observadores. Novos destinos de log podem ser adicionados ou removidos sem impactar diretamente o resto do código, o que promove maior escalabilidade e facilidade na manutenção.
