package edu.biniek.evoting.voting;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class VoterTest {

    @Test
    public void shouldVote() throws Exception {
        // given
        Voter voter = new Voter("Churchill");
        Voting voting = mock(Voting.class);
        // when
        voter.vote(voting);
        // then
        verify(voting, times(1)).vote(Mockito.any());
    }
}