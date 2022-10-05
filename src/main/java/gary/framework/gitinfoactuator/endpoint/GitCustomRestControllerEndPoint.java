package gary.framework.gitinfoactuator.endpoint;

import gary.framework.gitinfoactuator.model.GitInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RestControllerEndpoint(id = "custom-info")
public class GitCustomRestControllerEndPoint {

    @Value("${git.branch:#{null}}")
    private String branch;

    @Value("${git.commit.id:#{null}}")
    private String commitId;

    @Value("${git.commit.time:#{null}}")
    private String commitTime;

    @Value("${git.tags:#{null}}")
    private String tags;

    @Value("${git.commit.message.short:#{null}}")
    private String message;

    @GetMapping("/git/detail")
    public GitInfo getDetailInfo() {
        return GitInfo.builder()
                .branch(branch)
                .commitId(commitId)
                .commitTime(commitTime)
                .tags(tags)
                .message(message)
                .build();
    }

}
