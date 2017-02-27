package com.thomsonreuters.pageobjects.pages.polls;

import java.time.LocalDate;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Poll {
	
	private Integer id;
	private LocalDate creationDate;
	private Integer votesNumber;
	private String question;
	private Status status;
	
	public static enum Status {
		POLL_OPEN, POLL_HAS_CLOSED;
	}

	public Poll(Integer id, String question, Integer votesNumber, LocalDate creationDate, Status status) {
		this.id = id;
		this.question = question;
		this.votesNumber = votesNumber;
		this.creationDate = creationDate;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}
	
	public LocalDate getCreationDate() {
		return creationDate;
	}

	public Integer getVotesNumber() {
		return votesNumber;
	}

	public String getQuestion() {
		return question;
	}

	public Status getStatus() {
		return status;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Poll poll = (Poll) o;
        
    	return new EqualsBuilder()
    			.append(id, poll.getId())
    			.append(creationDate, poll.getCreationDate())
	    		.append(votesNumber, poll.getVotesNumber())
	    		.append(question, poll.getQuestion())
	    		.append(status, poll.getStatus())
	    		.isEquals();
    }

    @Override
    public int hashCode() {
    	return new HashCodeBuilder()
    			.append(id)
		    	.append(creationDate)
		    	.append(votesNumber)
		    	.append(question)
		    	.append(status)
		        .toHashCode();
    }
}