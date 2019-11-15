package fr.xgouchet.elmyr.regex

import fr.xgouchet.elmyr.Forger

/**
 * Represents a word character
 *
 * @author Xavier F. Gouchet
 */
class RegexWordCharNode(parent: RegexParentNode) :
    RegexNode(parent) {

    override fun buildIteration(forger: Forger, builder: StringBuilder) {
        builder.append(forger.anAlphaNumericalChar())
    }

    override fun describe(builder: StringBuilder) {
        builder.append("\\w")
    }
}
