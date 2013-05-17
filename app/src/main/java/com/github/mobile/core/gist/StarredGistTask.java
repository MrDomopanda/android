/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.mobile.core.gist;

import android.accounts.Account;
import android.content.Context;
import android.util.Log;

import com.github.mobile.accounts.AuthenticatedUserTask;
import com.google.inject.Inject;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.service.GistService;

/**
 * Task to check {@link Gist} starred status
 */
public class StarredGistTask extends AuthenticatedUserTask<Boolean> {

    private static final String TAG = "StarringGistTask";

    @Inject
    private GistService service;

    private final String id;

    /**
     * Create task to check starred status for a {@link Gist}
     *
     * @param context
     * @param id
     */
    public StarredGistTask(final Context context, final String id) {
        super(context);

        this.id = id;
    }

    @Override
    protected Boolean run(final Account account) throws Exception {
        return service.isStarred(id);
    }

    @Override
    protected void onException(final Exception e) throws RuntimeException {
        super.onException(e);

        Log.d(TAG, "Exception checking starredstatus for gist", e);
    }
}
