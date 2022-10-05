package gary.framework.gitinfoactuator.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.boot.info.GitProperties;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Properties;

@Component
@RestControllerEndpoint(id = "defined-info")
public class GitRestControllerEndPoint {

    private final GitProperties gitProperties;

    @Autowired
    public GitRestControllerEndPoint(@Autowired(required = false) GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @GetMapping("/git")
    public Object gitInfo() {

        if (ObjectUtils.isEmpty(gitProperties)) {
            return null;
        }

        return this.gitProperties;
    }

    @GetMapping("/git/detail")
    public Object getDetailInfo() {
        if (ObjectUtils.isEmpty(gitProperties)) {
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
