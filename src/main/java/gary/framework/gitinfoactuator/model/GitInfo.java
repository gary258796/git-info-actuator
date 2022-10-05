package gary.framework.gitinfoactuator.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GitInfo {

    private String branch;
    private String commitId;
    private String commitTime;
    private String tags;
    private String message;
}
