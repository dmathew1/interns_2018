package rahul_jenn;


import org.apache.log4j.Logger;

/**
 * Created by denze on 7/15/2018.
 */
public interface Loggable {
    default Logger logger(){
        return Logger.getLogger(this.getClass());
    }
}
