package backgroundcheck;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FullBackGroundCheck
{

    public List<String> runBackgroundCheck (Candidate candidate, List<CriteriaChecker> criteriaList)
    {
         return criteriaList.stream()
            .map(criterion -> criterion.evaluateCandidate(candidate))
            .filter(ApprovalStatus::isDisapproved)
            .map(ApprovalStatus::getReason)
            .map(reason -> reason.orElse(""))
            .collect(toList());
    }
}
