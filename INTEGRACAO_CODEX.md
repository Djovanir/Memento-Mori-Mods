# Implementação proposta: CountAway / Memento Mori

## Repositório de base
https://github.com/santirodriguez/CountAway (Apache-2.0)

## Status
Este pacote é um protótipo independente + componentes Kotlin de referência. **Não é um fork compilado nem um APK**. A árvore de fontes do projeto original não foi obtida no ambiente de criação; logo, classes, caminhos e APIs de integração **não foram confirmados**. Substitua o pacote `org.example.mementomori.theme` pelo pacote real depois de inspecionar o repositório. `index.html` executa isoladamente e não modifica CountAway.

## Objetivo do fork
Adicionar uma aparência selecionável **Memento Mori** ao app e aos widgets, preservando as funções existentes: contadores progressivos/regressivos, eventos, recorrência, backup, notificações e suporte offline. Não substituir a lógica de datas por um cronômetro em segundo plano. A implementação principal deve funcionar sem Internet.

## Requisitos de interface
- Paleta: preto `#101010`, painel `#171717`, bloco `#080808`, bronze `#EBA760`, texto secundário `#A9967E`, borda `#342A22`.
- Números tabulares, com agrupamento de milhares no padrão `pt-BR`.
- Cabeçalho `MEMENTO MORI`.
- Painel com dois contadores fixados pelo usuário: `FINITUDE` e `MARCO DO PROJETO`. Nenhum evento deve ser identificado por título mágico; permitir escolher os eventos nos ajustes.
- Estilo adicional de widget selecionável: fundo preto, números âmbar e rótulos discretos. O design deve caber em 1x1, 2x2 e widget expandido.
- Rótulos distintos para intervalos estimados e marcos reais.
- A contagem em **dias civis** deve permanecer correta após o aparelho desligar ou reiniciar, na virada do dia e em mudanças de fuso/horário de verão.
- Não coletar dados externos nem acrescentar permissões.

## Incrementos
1. **MVP visual:** criar opção de aparência Memento Mori para cartões existentes e widgets de evento único, sem mexer em dados.
2. **Painel duplo:** permitir selecionar dois IDs persistentes de eventos do CountAway. Um evento de finitude convencional e um de marco.
3. **Extensão opcional:** barra de percentual transcorrido quando houver data de origem e meta; ausência de datas deve ocultar o indicador, não produzir números inventados.

## Diretrizes de domínio
- Finitude é **horizonte convencional**, não uma previsão médica.
- Se a contagem atingir zero, mostrar 0 dias (ou `MARCO ALCANÇADO` se o fluxo do projeto optar), nunca valor negativo sem contexto.
- Na fase inicial, as datas são criadas/selecionadas no CountAway; não exigir data de nascimento ou guardá-la em um campo novo.
- Não marcar progresso diário automaticamente como desempenho do operador.

## Testes de aceitação
- Evento a mais de 9.999 dias continua correto (ex.: 2050/2060).
- Widget reflete mudança de data/evento, após reinicialização e ao virar o dia.
- Mudanças de fuso: cálculo coerente com a data local e as convenções existentes no CountAway.
- Eventos recorrentes, contagens progressivas e modo escuro originais continuam funcionando.
- Com evento apagado, painel não deve quebrar: estado de escolha/ausência explícito.
- Acessibilidade: contraste suficiente, fonte ampliada, descrição para leitor de tela e sem informação dependente apenas de cor.
- Aplicativo original e fork assinado com certificados diferentes não podem compartilhar o mesmo applicationId na instalação: mudar applicationId se desejar instalação paralela; para substituir o original, exportar dados via backup quando possível.

## Como pedir ao Codex para integrar (a partir da pasta com o clone)

> Inspecione a arquitetura atual deste repositório CountAway. Implemente um tema opcional chamado "Memento Mori" para o app e os widgets sem reescrever o cálculo de datas nem afetar os estilos anteriores. Use `android/MementoMoriPalette.kt` e `android/MementoMoriPanel.kt` como referência visual, mas adapte seus pacotes, componentes e arquitetura ao projeto real. Comece por um novo estilo para widget de evento único. Depois faça um painel com dois eventos escolhidos pelo usuário. Mantenha armazenamento local, testes e tradução pt-BR. Rode `./gradlew test lint assembleDebug` e relate erros objetivos e limitações; não afirme sucesso sem compilação. Veja os critérios de `INTEGRACAO_CODEX.md`.

## Compilação
O README do CountAway informa JDK 17 e Android SDK 36; requer o repositório completo. Rodar:

```sh
./gradlew test lint assembleDebug
```

O APK debug, quando compilado com sucesso, costuma ficar sob `app/build/outputs/apk/debug/`. Conferir o caminho efetivo no projeto.
