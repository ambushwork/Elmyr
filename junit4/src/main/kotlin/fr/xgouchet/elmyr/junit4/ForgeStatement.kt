package fr.xgouchet.elmyr.junit4

import fr.xgouchet.elmyr.Forge
import fr.xgouchet.elmyr.inject.DefaultForgeryInjector
import fr.xgouchet.elmyr.inject.ForgeryInjector
import java.util.Locale
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.MultipleFailureException
import org.junit.runners.model.Statement

internal class ForgeStatement(
    private val base: Statement,
    private val method: FrameworkMethod,
    private val target: Any,
    private val forge: Forge
) : Statement() {

    private val injector: ForgeryInjector = DefaultForgeryInjector()

    // region Statement

    /** @inheritdoc */
    @Suppress("TooGenericExceptionCaught")
    override fun evaluate() {
        val errors = mutableListOf<Throwable>()

        performQuietly(errors) { starting() }

        try {
            injector.inject(forge, target)
            base.evaluate()
            performQuietly(errors) { succeeded() }
        } catch (e: org.junit.internal.AssumptionViolatedException) {
            errors.add(e)
            performQuietly(errors) { skipped() }
        } catch (e: Throwable) {
            errors.add(e)
            performQuietly(errors) { failed() }
        } finally {
            performQuietly(errors) { finished() }
        }

        MultipleFailureException.assertEmpty(errors)
    }

    // endregion

    // region Internal

    @Suppress("TooGenericExceptionCaught")
    private fun performQuietly(
        errors: MutableList<Throwable>,
        operation: () -> Unit
    ) {
        try {
            operation()
        } catch (e: Throwable) {
            errors.add(e)
        }
    }

    @Suppress("EmptyFunctionBlock")
    private fun starting() {
    }

    @Suppress("EmptyFunctionBlock")
    private fun succeeded() {
    }

    @Suppress("EmptyFunctionBlock")
    private fun skipped() {
    }

    private fun failed() {
        val message = "<%s.%s()> failed with Forge seed 0x%xL\n" +
                "Add this seed in the ForgeRule in your test class :\n\n" +
                "\tForgeRule forge = new ForgeRule(0x%xL);\n"
        System.err.println(
                message.format(
                        Locale.US,
                        target.javaClass.simpleName,
                        method.name,
                        forge.seed,
                        forge.seed
                )
        )
    }

    @Suppress("EmptyFunctionBlock")
    private fun finished() {
    }

    // endregion

    companion object {
        const val SEED_MASK = 0x7FFFFFFFL
    }
}
