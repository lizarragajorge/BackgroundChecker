package backgroundcheck;

public interface CriteriaChecker
{
    ApprovalStatus evaluateCandidate(Candidate candidate);

}
