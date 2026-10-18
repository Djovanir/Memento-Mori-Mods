// Componente Compose independente para integração futura.
// O cálculo dos dias DEVE vir da lógica existente do CountAway (não criar contadores paralelos).
package org.example.mementomori.theme // SUBSTITUIR pelo pacote real do CountAway

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

private val ptBR = Locale.forLanguageTag("pt-BR")

/** Recebe dias CIVIS calculados pela fonte de dados original do CountAway. */
@Composable
fun MementoMoriPanel(
    lifeDaysRemaining: Long?,
    projectDaysRemaining: Long?,
    lifeElapsedPercent: Float? = null,
    modifier: Modifier = Modifier,
) {
    val p = MementoMoriPalette
    Column(
        modifier = modifier.fillMaxWidth()
            .background(p.Surface, RoundedCornerShape(24.dp))
            .border(1.dp, p.Border, RoundedCornerShape(24.dp))
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "MEMENTO MORI",
            color = p.SecondaryText,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 2.sp,
        )
        // Coluna é intencionalmente melhor em telas pequenas; widgets 2 x 1 são
        // renderizados separadamente, ou o layout pode mudar com largura disponível.
        CounterCard("FINITUDE", lifeDaysRemaining, "DIAS ESTIMADOS")
        CounterCard("MARCO DO PROJETO", projectDaysRemaining, "DIAS RESTANTES")
        if (lifeElapsedPercent != null) {
            Text("HORIZONTE CONVENCIONAL DE VIDA", style = MaterialTheme.typography.labelSmall, color = p.SecondaryText)
            LinearProgressIndicator(
                progress = { lifeElapsedPercent.coerceIn(0f, 1f) },
                modifier = Modifier.fillMaxWidth(),
                color = p.Accent,
                trackColor = p.Border,
            )
        }
        Text(
            "Tempo não é meta.",
            color = p.Muted,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Composable
private fun CounterCard(title: String, remaining: Long?, subtitle: String) {
    val p = MementoMoriPalette
    Column(
        modifier = Modifier.fillMaxWidth().background(p.Card, RoundedCornerShape(18.dp)).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(title, style = MaterialTheme.typography.titleSmall, color = p.SecondaryText, letterSpacing = 1.sp)
        Text(
            remaining?.let { NumberFormat.getIntegerInstance(ptBR).format(it.coerceAtLeast(0)) } ?: "—",
            fontSize = 44.sp, lineHeight = 50.sp, fontWeight = FontWeight.SemiBold,
            color = p.Accent, maxLines = 1, overflow = TextOverflow.Ellipsis,
        )
        Text(subtitle, style = MaterialTheme.typography.labelMedium, color = p.SecondaryText)
    }
}
