/*
 * MIT License
 *
 * Copyright (c) 2019 Shimmermare
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.shimmermare.inviteroles;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class InviteRoles
{
    private static final Logger LOGGER = LoggerFactory.getLogger(InviteRoles.class);

    private final String token;

    private JDA jda;

    public InviteRoles(String token)
    {
        this.token = token;
    }

    private void run()
    {
        LOGGER.info("Starting InviteRoles Discord Bot. I'm alive!");

        JDABuilder jdaBuilder = new JDABuilder(token);

        try
        {
            jda = jdaBuilder.build();
        }
        catch (LoginException e)
        {
            LOGGER.error("Unable to login to Discord", e);
            return;
        }

        try
        {
            System.in.read();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            LOGGER.info("Application terminated from console. Goodbye!");
        }
    }

    public static void main(String[] args)
    {
        OptionParser optionParser = new OptionParser();
        OptionSpec<String> tokenSpec = optionParser.accepts("token").withRequiredArg().ofType(String.class).required();
        OptionSet optionSet = optionParser.parse(args);

        String token = tokenSpec.value(optionSet);

        InviteRoles inviteRoles = new InviteRoles(token);
        inviteRoles.run();
    }
}