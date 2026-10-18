# Memento Mori — protótipo para personalizar CountAway

Este diretório contém um **protótipo HTML offline e funcional** de um painel inspirado na interface que você aprovou, além de dois componentes Kotlin de referência e um guia para implementar um tema selecionável no projeto de código aberto CountAway.

**Como testar:** abrir `index.html` num navegador; clicar em Configurar; inserir data de nascimento, idade de referência e marco do projeto. As preferências são salvas localmente no navegador (localStorage). Não há conexão de rede. Sem configuração, os números são meramente ilustrativos.

**Importante:** a contagem de finitude usa uma idade de referência convencionada por você, não uma previsão de morte. Ela indica dias até uma data definida. O painel calcula dias civis a partir da data atual; não depende de rodar continuamente em segundo plano.

**Não é um APK e não altera o aplicativo original.** Para levar o conceito ao Android, consulte `INTEGRACAO_CODEX.md`. Uma implementação real precisa primeiro ler e modificar os arquivos do CountAway.

**Licenças:** os arquivos originais criados para este protótipo podem ser utilizados e adaptados. Ao gerar e distribuir um fork do CountAway, manter os avisos da licença Apache-2.0 do projeto original e as atribuições exigidas.
