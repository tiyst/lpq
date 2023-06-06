package st.tiy.lpq.utils.environments;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * For beans that should be available only on local / dev environments
 */
@Retention(RetentionPolicy.RUNTIME)
@Profile("dev")
public @interface Dev {
}
