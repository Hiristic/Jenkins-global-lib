FROM ubuntu:16.04

RUN apt-get update && apt-get install -q -y \
    git \
    locales \
    python3 \
    python3-pip \
    sshpass \
    tzdata \
    wget \
    iputils-ping \
    vim \
    emacs \
    curl \
&& rm -rf /var/lib/apt/lists/*

# Setup locale
RUN locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV LC_MESSAGES en_US.UTF-8

# Set container timezone to something more likely to coincide with host timezone
ENV TZ Europe/Stockholm

RUN pip3 install --upgrade pip

# Install pip dependencies
COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt

# Add "python" as an alias for python3
RUN ln -s /usr/bin/python3 /usr/bin/python

