package gary.framework.gitinfoactuator.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.info.GitProperties;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Properties;

@Component
@WebEndpoint(id = "git-info")
public class GitEndPoint {

    private final GitProperties gitProperties;

    @Autowired
    public GitEndPoint(@Autowired(required = false) GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @ReadOperation
    public GitProperties getGitInfo() {

        if (ObjectUtils.isEmpty(gitProperties)) {
            return null;
        }

        return this.gitProperties;
    }

    /**
     * Return detail git info if ${showDetail} is true.
     * @return detail git information
     */
    @ReadOperation
    public Object getGitInfo(@Selector boolean showDetail) {

        if (ObjectUtils.isEmpty(gitProperties) || !showDetail) {
            return null;
        }

        return getGitDetail();
    }

    /**
     * Get all properties from git.properties file
     */
    private Object getGitDetail() {

        Properties props;

        try {
            props = PropertiesLoaderUtils.loadAllProperties("git.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return props;
    }
}
